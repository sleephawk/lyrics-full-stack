import "../style.scss";

const submitSongForm =
  document.querySelector<HTMLFormElement>("#submit-song-form");

if (!submitSongForm) {
  throw new Error("Missing DOM elements on submit page");
}

submitSongForm.addEventListener("submit", async (e) => {
  e.preventDefault();
  console.log("button has been pressed!");

  const form = e.target as HTMLFormElement;
  const formData = new FormData(form); //learnt this today

  const name = formData.get("name") as string;
  const authorNames: string = formData.get("authorNames") as string;
  const releaseYear = formData.get("releaseYear") as string;
  const genreNames = formData.get("genreNames") as string;
  const lyrics = formData.get("lyrics") as string;

  console.log({ name, authorNames, releaseYear, genreNames, lyrics });

  const response = await fetch("localhost:8080/api/songs/submit", {
    method: "POST",
    body: JSON.stringify({
      name: name,
      authorNames: authorNames,
      releaseYear: releaseYear,
      genreNames: genreNames,
      lyrics: lyrics,
    }),
  });
  if (response.ok) {
    confirm("That's all good! Thanks for submitting a song");
  } else {
    alert(
      "There's been some problem with this addition on our side, please try again later"
    );
  }
});

// {
//   "name": "Bohemian Rhapsody",
//   "lyrics": "Is this the real life? Is this just fantasy?...",
//   "releaseYear": 1975,
//   "authorNames": ["Freddie Mercury", "Queen"],
//   "genreNames": ["Rock", "Progressive Rock"]
// }
