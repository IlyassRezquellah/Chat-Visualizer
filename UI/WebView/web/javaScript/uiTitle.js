function createChatTittle(id, text){
  var getParent = document.getElementById(id);
  var getFirstChild = getParent.childNodes.length;
  var element = document.createElement("p");
  element.setAttribute("id", id+"Tittle");
  element.appendChild(document.createTextNode(text));
  //document.getElementById('chartCountMessages').appendChild(element);
  getParent.insertBefore(element, getParent.childNodes[0]);
}