import "../style.scss";
import { sanitiseInput } from "./utils";
// import { sleep } from "./utils";

//UTILS

const submitSongForm =
  document.querySelector<HTMLFormElement>("#submit-song-form");
const errorMessage =
  document.querySelector<HTMLParagraphElement>("#error-message");

if (!submitSongForm || !errorMessage) {
  throw new Error("Missing DOM elements on submit page");
}

submitSongForm.addEventListener("submit", async (e) => {
  e.preventDefault();
  console.log("button has been pressed!");

  const form = e.target as HTMLFormElement;
  const formData = new FormData(form); //learnt this today

  const name = formData.get("name") as string;
  const artistNames: string = formData.get("artistNames") as string;
  const releaseYear = formData.get("releaseYear") as string;
  const genreNames = formData.get("genreNames") as string;
  const lyrics = formData.get("lyrics") as string;

  console.log({ name, artistNames, releaseYear, genreNames, lyrics });

  const response = await fetch("localhost:8080/api/songs", {
    method: "POST",
    body: JSON.stringify({
      name: name,
      artistNames: artistNames,
      releaseYear: releaseYear,
      genreNames: genreNames,
      lyrics: lyrics,
    }),
  });
  if (response.ok) {
    /*200*/ form.style.display = "none";
    errorMessage.textContent =
      "Thanks for your contribution! We appreciate your support.";
  } else {
    alert(
      "There's been some problem with this addition on our side, please try again later"
    );
    //recreate manually // bring in sleep timer // hide form
  }
});

// {
//   "name": "Bohemian Rhapsody",
//   "lyrics": "Is this the real life? Is this just fantasy?...",
//   "releaseYear": 1975,
//   "authorNames": ["Freddie Mercury", "Queen"],
//   "genreNames": ["Rock", "Progressive Rock"]
// }
