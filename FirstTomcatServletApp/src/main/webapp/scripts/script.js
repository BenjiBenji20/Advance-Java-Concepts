const greetElement = document.getElementById("greeting");

greetElement.addEventListener('click', () => {
    greetElement.innerHTML = 'How are yesss';
    document.body.style.background = 'black';
    greetElement.style.color = 'white';
});