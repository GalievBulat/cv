
function onPrint(){

    let pas = document.getElementById("password_input").value;
    if(pas.length <=8){
        let pasHolder= document.getElementById("invalid password");
        pasHolder.innerText = "";
        let el = document.createElement("p");
        el.innerText =
            decodeURI("%D1%81%D0%BB%D0%B8%D1%88%D0%BA%D0%BE%D0%BC%20%D0%BA%D0%BE%D1%80%D0%BE%D1%82%D0%BA%D0%B8%D0%B9%20%D0%BF%D0%B0%D1%80%D0%BE%D0%BB%D1%8C");
        pasHolder.appendChild(el);
    }else {
        let pasHolder= document.getElementById("invalid password");
        pasHolder.innerText = "";
    }
}