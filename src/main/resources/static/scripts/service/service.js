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

  /**
   * delete specific user via username
   */
  static async deleteUserService(deleteUser) {
    const response = await fetch("http://localhost:8080/api/user/delete", {
      method: "DELETE",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(deleteUser),
    });
  
    if (!response.ok) return false; // return false if request failed
  
    return response.status === 200 ? true : false; 
  }

  /**
   * UPDATE
   */
  static async updateUserService(confirmationUsername, confirmationPassword) {
    const response = await fetch("http://localhost:8080/api/user/update", {
      method: "PUT",
      headers: {"Content-Type": "application/json"},
      body: JSON.stringify(confirmationUsername, confirmationPassword)
    });

    return response.json();
  }

  /**
   * Get all users data
   */
  static async getAllUsersService() {
    const response = await fetch("http://localhost:8080/api/user/user-list", {
      headers: {"Content-Type": "application/json"},
    });

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }

    return response.json();
  }

  /**
   * SEARCH user from list
   */
  static async searchUserService(keyword) {
    const response = await fetch(`http://localhost:8080/api/user/search/${keyword}`, {
      headers: {"Content-Type": "application/json"},
    });
    
    return response.json();
  }
}