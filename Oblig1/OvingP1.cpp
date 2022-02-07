#include <iostream>
#include <thread>
#include <vector>
#include <atomic>
#include <algorithm>
#include <mutex>

using namespace std;
vector<thread> threads;

bool calcPrime(int n){
    bool isPrime = true;

    if(n == 0 || n == 1){
        isPrime = false;
    } else if(n == 2){
        isPrime = true;
    } else {
        for(int i = 2; i < n; i++){
            if(n%i == 0){
                isPrime = false;
                break;
            }
        }
    }
    if(isPrime){
        return true;
    } else {
        return false;
    }
}

void primeNumbers(int number1, int number2, int threadN){
    int threadGroups = (number2 - number1) / threadN;
    vector<int> primes;
    mutex primes_mutex;

    for(int thread_number = 0; thread_number < threadN; ++thread_number){
        threads.emplace_back([thread_number, &threadGroups, &primes, &number1, &number2, &threadN, &primes_mutex] {
            int start = thread_number * threadGroups + number1;

            if(thread_number == threadN-1){
                for(int i = start; i <= number2; ++i){
                    if(calcPrime(i)){
                        primes_mutex.lock();
                        primes.push_back(i);
                        primes_mutex.unlock();
                    }
                }
            }
            else{
                for(int i = start; i < start + threadGroups; ++i){
                    if(calcPrime(i)){
                        primes_mutex.lock();
                        primes.push_back(i);
                        primes_mutex.unlock();
                }
            }
            }
        });
    }

    for(auto& thread : threads){
        thread.join();
    }
    sort(primes.begin(), primes.end());

    for(auto x : primes){
        cout << x << ", ";
    }
}

int main(){
    int start = 0;
    int end = 101;
    int n = 10;

    threads.reserve(n);
    
    primeNumbers(start, end, n);
    return 0;
}