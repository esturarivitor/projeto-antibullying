fetch("navbar.html")
  .then((response) => response.text())
  .then((data) => {
    document.getElementById("header-placeholder").innerHTML = data;
  });

document.addEventListener("DOMContentLoaded", () => {
  const searchBox = document.querySelector(".search-box");
  const cards = document.querySelectorAll(".card");

  searchBox.addEventListener("input", function () {
    const termo = this.value
      .toLowerCase()
      .normalize("NFD")
      .replace(/[\u0300-\u036f]/g, "");

    cards.forEach((card) => {
      const texto = card.textContent
        .toLowerCase()
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "");
      card.style.display = texto.includes(termo) ? "block" : "none";
    });
  });
});