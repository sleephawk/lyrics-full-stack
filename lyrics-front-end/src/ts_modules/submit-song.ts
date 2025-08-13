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
  const authorNames = formData.get("authorNames") as string;
  const releaseYear = formData.get("releaseYear") as string;
  const genreNames = formData.get("genreNames") as string;
  const lyrics = formData.get("lyrics") as string;

  console.log({ name, authorNames, releaseYear, genreNames, lyrics });

  // try {
  //   const song = await searchSong(lyricForm.value);
  //   responseDisplay.innerHTML = `
  //   Song: ${song.song}<br><br>
  //   Artist: ${song.artist}<br><br>
  //   Year: ${song.year}<br><br>
  //   Lyrics:\n${song.lyrics}<br><br>
  //   Genre: ${song.genre}`;

  //   displayArea.style.opacity = "1";
  //   quickMessage.style.opacity = "1";
  //   await sleep(300);
  //   responseDisplay.style.opacity = "1";
  // } catch (error) {
  //   return "No luck this time";
  // }
});

// {
//   "name": "Bohemian Rhapsody",
//   "lyrics": "Is this the real life? Is this just fantasy?...",
//   "releaseYear": 1975,
//   "authorNames": ["Freddie Mercury", "Queen"],
//   "genreNames": ["Rock", "Progressive Rock"]
// }
