<template>
  <div>
    <InputComponent />
    <h3>Output:</h3>
    <input type="text" />
  </div>
</template>

<script>
import InputComponent from "../components/InputComponent";
export default {
  name: "CompilerView",
  components: { InputComponent },
};
</script>

<style scoped>

</style>