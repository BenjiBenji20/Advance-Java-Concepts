export class ServiceAPI {
  static async  registerUser(userInputs) {
    const response = await fetch("http://localhost:8080/api/user/registration", {
      method: "POST",
      headers: {"Content-Type": "application/json"},
      body: JSON.stringify(userInputs)
    });
  
    return response.json();
  }
}