<template>
  <div id="app">
    <CompilerView />
  </div>
</template>

<script>
import CompilerView from "views/CompilerView";
export default {
  name: "App",
  components: { CompilerView },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
