<template>
  <div id="box">
    <div id="inputBox">
      <h3>Input:</h3>
      <textarea id="input" v-model="codeInput" />
      <button type="submit" id="button" v-on:click="onSubmit">
        Compile and run
      </button>
    </div>
    <div id="outputBox">
      <h3>Output:</h3>
      <textarea disabled v-model="codeOutput"/>
    </div>
  </div>
</template>

<script>
import { compile } from "../utils/apiutil.js";
export default {
  name: "InputComponent",
  data() {
    return {
      codeInput: "",
      codeOutput: "",
    };
  },
  methods: {
    async onSubmit() {
      const response = await compile(this.codeInput);
      this.codeOutput = response.data;
    },
  },
};
</script>

<style scoped>
#box {
  display: flex;
  flex-direction: column;
  width: 500px;
  margin: auto;
}

#button {
  cursor: pointer;
  margin-top: 10px;
  padding: 10px;
  font-size: 18px;
  background-color: dodgerblue;
  color: white;
  border: none;
  border-radius: 10px;
  width: 175px;
}
textarea {
  width: 500px;
  height: 200px;
}
</style>
