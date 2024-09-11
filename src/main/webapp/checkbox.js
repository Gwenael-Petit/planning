/*document.querySelector(".lam").addEventListener("click",function(){
    click(event,"lundiTimeAm","lam");
});
document.querySelector(".lap").addEventListener("click",function(){
    click(event,"lundiTimeAp","lap");
});



document.querySelector(".meam").addEventListener("click",function(){
    click(event,"mercrediTimeAm","meam");
});
document.querySelector(".meap").addEventListener("click",function(){
    click(event,"mercrediTimeAp","meap");
});

document.querySelector(".jam").addEventListener("click",function(){
    click(event,"jeudiTimeAm","jam");
});
document.querySelector(".jap").addEventListener("click",function(){
    click(event,"jeudiTimeAp","jap");
});

document.querySelector(".vam").addEventListener("click",function(){
    click(event,"vendrediTimeAm","vam");
});
document.querySelector(".vap").addEventListener("click",function(){
    click(event,"vendrediTimeAp","vap");
});

document.querySelector(".sam").addEventListener("click",function(){
    click(event,"samediTimeAm","sam");
});
document.querySelector(".sap").addEventListener("click",function(){
    click(event,"samediTimeAp","sap");
});

document.querySelector(".dam").addEventListener("click",function(){
    click(event,"dimancheTimeAm","dam");
});
document.querySelector(".dap").addEventListener("click",function(){
    click(event,"dimancheTimeAp","dap");
}); */
document.querySelector(".lundi").addEventListener("click",function(){
    click(event,"lundiTime","lundi");
});
document.querySelector(".mardi").addEventListener("click",function(){
    click(event,"mardiTime","mardi");
});
document.querySelector(".mercredi").addEventListener("click",function(){
    click(event,"mercrediTime","mercredi");
});
document.querySelector(".jeudi").addEventListener("click",function(){
    click(event,"jeudiTime","jeudi");
});
document.querySelector(".vendredi").addEventListener("click",function(){
    click(event,"vendrediTime","vendredi");
});
document.querySelector(".samedi").addEventListener("click",function(){
    click(event,"samediTime","samedi");
});
document.querySelector(".dimanche").addEventListener("click",function(){
    click(event,"dimancheTime","dimanche");
});


document.querySelector(".matin").addEventListener("click",function(){
    click(event,"matinTime","matin");
});
document.querySelector(".aprem").addEventListener("click",function(){
    click(event,"apremTime","aprem");
});

function click(event,jour,moment){
    
    const checkBox = document.querySelector(`.${moment}`);
    const time = document.querySelectorAll(`#${jour}`);
    if(checkBox.checked){
        time.forEach(time => time.setAttribute("checked",""));
        time.forEach(time => time.removeAttribute("disabled"));
    }else{
        time.forEach(time => time.setAttribute("disabled",""));
        time.forEach(time => time.removeAttribute("checked"));
    }
}