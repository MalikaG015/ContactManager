console.log("script loaded");


let currentTheme=getTheme();
console.log(currentTheme);
document.addEventListener("DOMContentLoaded",()=>{
    changeTheme();
})


function changeTheme(){
    //set to web page
    document.querySelector("html").classList.add(currentTheme);
    //set the listener
    const changeThemeButton=document.querySelector('#theme_change_button');
    changeThemeButton.addEventListener('click',(event)=>{
        console.log('button clicked')
        const oldTheme=currentTheme;
        console.log('11111',oldTheme)
        
        if(currentTheme==="dark"){
            currentTheme="light";
        }
        else{
            currentTheme="dark";
        }
    //local storage update
    setTheme(currentTheme);
    document.querySelector("html").classList.remove(oldTheme);
    document.querySelector("html").classList.add(currentTheme);
    //change text of button
    changeThemeButton.querySelector('span').textContent=currentTheme=="light"?"Dark":"Light";

    })

}
//set theme to local storage
function setTheme(theme){
    localStorage.setItem("theme",theme);
}

//get theme ftom local storage
function getTheme(){
    let theme=localStorage.getItem("theme");
    if(theme){
        return theme;
    }
    else{
        return "light";
    }
}