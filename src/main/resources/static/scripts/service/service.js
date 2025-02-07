export class ServiceAPI {
  /**
   * User registration fetch json from the backend to post a req
   */
  static async  registerUserService(userInputs) {
    const response = await fetch("http://localhost:8080/api/user/registration", {
      method: "POST",
      headers: {"Content-Type": "application/json"},
      body: JSON.stringify(userInputs)
    });
  
    return response.json();
  }
  /**
   * User login fetch json from backend to post a req
   */
  static async authUserService(userCredentials) {
    const response = await fetch("http://localhost:8080/api/user/auth", {
      method: "POST",
      headers: {"Content-Type": "application/json"},
      body: JSON.stringify(userCredentials)
    });

    return response.json();
  }
}