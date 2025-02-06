const containerElement = document.querySelector('.container');

document.getElementById("registration-form-js").addEventListener('submit', async (e) => {
  e.preventDefault(); // prevent from submitting if not input all fields

  const user = {
    completeName: document.getElementById("complete-name").value,
    username: document.getElementById("username").value,
    password: document.getElementById("password").value
  };

  const response = await fetch("http://localhost:8080/api/user/registration", {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify(user)
  });

  const result = await response.text();
  console.log(result);

  if(result) {
    containerElement.innerHTML = ''; // clear contentif successful registration 
  }
});