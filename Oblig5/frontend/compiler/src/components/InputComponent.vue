<template>
  <div>
    <h3>Input:</h3>
    <input type="text" id="input" v-model="codeInput" />
    <input type="submit" value="Compile and run" />
  </div>
</template>

<script>
export default {
  name: "InputComponent",
  data() {
    return {
      codeInput: "",
    };
  },
  methods: {
    onSubmit(){

    }
  }
};
</script>

<style scoped>
#input {
  width: 200px;
  height: 200px;
}
</style>
