import "./style.scss";

//Query Selectors

const submit = document.querySelector<HTMLButtonElement>("#submit");
const displayArea = document.querySelector<HTMLDivElement>(".display-area");
const responseDisplay =
  document.querySelector<HTMLParagraphElement>("#display-response");
const quickMessage = document.querySelector<HTMLParagraphElement>(
  ".display-area--quick-message"
);

if (
  !submit ||
  !responseDisplay ||
  !responseDisplay ||
  !quickMessage ||
  !displayArea
) {
  throw new Error("Some DOM elements are missing.");
}

//UTILS
const cleanInput = (input: string): string => {
  return input.trim().replace(/\s+/g, " ");
};

const sleep = (ms: number) => new Promise((resolve) => setTimeout(resolve, ms));

//API Handling
interface SongDetails {
  id: number;
  song: string;
  artist: string;
  year: number;
  lyrics: string;
  genre: string;
}

const searchSong = async (input: string): Promise<SongDetails> => {
  const lyric: string = cleanInput(input);
  const url: URL = new URL();
  // "https://mocki.io/v1/0340b343-1ca6-486e-998f-f55567ea0536"
  url.searchParams.append("search", lyric);
  const response = await fetch(url.toString());
  if (!response.ok)
    throw new Error(
      "Could not find this song, are you sure you typed it correctly?"
    );
  const songDetails: SongDetails = await response.json();
  return songDetails;
};
submit.addEventListener("click", async (e) => {
  e.preventDefault();
  console.log("button has been pressed!");

  const lyricForm = document.querySelector(
    "#input-field"
  ) as HTMLTextAreaElement;

  try {
    const song = await searchSong(lyricForm.value);
    responseDisplay.innerHTML = `
    Song: ${song.song}<br><br>
    Artist: ${song.artist}<br><br>
    Year: ${song.year}<br><br>
    Lyrics:\n${song.lyrics}<br><br>
    Genre: ${song.genre}`;

    displayArea.style.opacity = "1";
    quickMessage.style.opacity = "1";
    await sleep(300);
    responseDisplay.style.opacity = "1";
  } catch (error) {
    return "No luck this time";
  }
});

/** TEST DATA
 * 
 * {
  "id": 1,
  "song": "Wonderwall",
  "artist": "Oasis",
  "year": 1995,
  "lyrics": "Today is gonna be the day\nThat they're gonna throw it back to you\nBy now you should've somehow\nRealized what you gotta do\nI don't believe that anybody\nFeels the way I do about you now\n\nBackbeat, the word is on the street\nThat the fire in your heart is out\nI'm sure you've heard it all before\nBut you never really had a doubt\nI don't believe that anybody feels\nThe way I do about you now\n\nAnd all the roads we have to walk are winding\nAnd all the lights that lead us there are blinding\nThere are many things that I would\nLike to say to you\nBut I don't know how\n\nBecause maybe\nYou're gonna be the one that saves me\nAnd after all\nYou're my wonderwall\n\nToday was gonna be the day\nBut they'll never throw it back to you\nBy now you should've somehow\nRealized what you're not to do\nI don't believe that anybody\nFeels the way I do\nAbout you now\n\nAnd all the roads that lead you there were winding\nAnd all the lights that light the way are blinding\nThere are many things that I would like to say to you\nBut I don't know how\n\nI said maybe\nYou're gonna be the one that saves me\nAnd after all\nYou're my wonderwall\n\nI said maybe (I said maybe)\nYou're gonna be the one that saves me\nAnd after all\nYou're my wonderwall\n\nI said maybe (I said maybe)\nYou're gonna be the one that saves me (saves me)\nYou're gonna be the one that saves me (saves me)\nYou're gonna be the one that saves me (saves me)",
  "genre": "Brit-pop"
}
 */

/** YOUTUBE URL GEN
 * https://www.youtube.com/results?search_query=YOUR+SONG+NAME
 */
