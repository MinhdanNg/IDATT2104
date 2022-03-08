<template>
  <div id="app">
    <Compiler />
  </div>
</template>

<script>
import Compiler from "@/components/CompilerComponent.vue";
export default {
  name: "App",
  components: { Compiler },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
