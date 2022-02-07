#include <thread>
#include <mutex>
#include <condition_variable>
#include <vector>
#include <iostream>
#include <atomic>
#include <list>
#include <functional>

using namespace std;

class Workers {
    condition_variable cv;
    vector<thread> worker_threads;
    list<function<void ()>> tasks;
    int thread_amount;
    mutex tasks_mutex;

    atomic<bool> wait{true};
    atomic<bool> run{true};
    
    public:
        Workers(int threads){
            thread_amount = threads;
        }
    void start(){
        run = true;
        for (int i = 0; i < thread_amount; i++) {
            worker_threads.emplace_back([this] {
                while (run) {
                    function<void()> task;
                    {
                        unique_lock<mutex> lock(tasks_mutex);
                        while(wait){
                            cv.wait(lock);
                        }
                        if (!tasks.empty()) {
                            task = *tasks.begin(); // Copy task for later use
                            tasks.pop_front(); // Remove task from list
                        }
                    }
                    if (task)
                    task(); // Run task outside of mutex lock
                }
            });
            {
                unique_lock<mutex> lock(tasks_mutex);
                wait = false;
            }
            cv.notify_one(); // Awake waiting cv
        }
    }

    void post(function<void()> func){
        unique_lock<mutex> lock(tasks_mutex);
        tasks.emplace_back(func);
        cv.notify_one();
    }

    void post_timeout(function<void()> func, long timeout){
        tasks.emplace_back([func, timeout]{
            this_thread::sleep_for(chrono::milliseconds(timeout));
            func();
        });
    }

    void stop(){
        if(run){
            run = false;
        }
    }

    void join(){
        for (auto &thread : worker_threads){
            thread.join();
        }
    }
};

int main() {
    Workers worker_threads(4);
    Workers event_loop(1);

    worker_threads.start(); // Create 4 internal threads
    event_loop.start(); // Create 1 internal thread

    worker_threads.post([] {
    cout << "Task A running \n"; // Task A
    });

    worker_threads.post([] {
    cout << "Task B running \n"; // Task B
    // Might run in parallel with task A
    });

    event_loop.post([] {
    cout << "Task C running \n"; // Task C
    // Might run in parallel with task A and B
    });

    event_loop.post([] {
    cout << "Task D running \n"; // Task D
    // Will run after task C
    // Might run in parallel with task A and B
    });

    worker_threads.post_timeout([]{
        cout << "Delayed task running \n"; 
    }, 1000);

    this_thread::sleep_for(chrono::seconds(3));

    worker_threads.stop();
    event_loop.stop();

    worker_threads.join(); // Calls join() on the worker threadsevent_loop.join(); // Calls join() on the event thread
    event_loop.join();
    return 0;
}
