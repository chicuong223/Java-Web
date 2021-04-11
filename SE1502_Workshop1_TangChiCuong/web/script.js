/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global clear */


const catBtns = document.querySelectorAll(".catBtn");
catBtns.forEach(btn => {
   var catDiv = document.getElementById(btn.value);
   btn.addEventListener('click', ()=>{
        catDiv.classList.toggle("catDiv");
        catDiv.classList.toggle("cat");
   });
});
