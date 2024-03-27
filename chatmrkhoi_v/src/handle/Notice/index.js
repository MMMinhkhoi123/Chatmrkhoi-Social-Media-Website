import { h } from "vue";

export default () => {


    function run(title){
        const parent = document.querySelector(".listAlert");
        parent.append(Alert(title));
    }

function Alert(title) {
    const divAlert =  document.createElement("div");
    divAlert.classList.add("ALert__title")
    divAlert.style.minWidth = '200px';
    divAlert.style.background = 'white';
    divAlert.style.padding = " 10px 20px";
    divAlert.style.borderRadius = " 10px";
    divAlert.style.animation = "hiden 2s ease forwards";

    const h4 =  document.createElement("h4");
    h4.classList.add("ALert__title")
    h4.innerText = title;
    h4.style.margin = "0";
    h4.style.padding  =  "0";

    const p = document.createElement("p");
    p.classList.add("ALert__content");
    p.innerText = "Bạn vừa nhận được một thông báo mới !"

    divAlert.append(h4);
    divAlert.append(p);

    return divAlert;
}

  return {
    run
  };
}