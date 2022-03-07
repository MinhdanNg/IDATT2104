import axios from "axios";

export function compile(code) {
  const headers = {
    "Content-Type": "application/json"
  }
  return axios
    .post("http://localhost:8080/compiler", {code:code}, { headers })
    .then((response) => {
      return response;
    });
}