import "../style.scss";
import { sleep } from "./utils";

//Query Selectors

const searchLyricSubmit =
  document.querySelector<HTMLButtonElement>("#searchLyricSubmit");
const displayArea = document.querySelector<HTMLDivElement>(".display-area");
const responseDisplay =
  document.querySelector<HTMLParagraphElement>("#display-response");
const quickMessage = document.querySelector<HTMLParagraphElement>(
  ".display-area--quick-message"
);

if (
  !searchLyricSubmit ||
  !responseDisplay ||
  !responseDisplay ||
  !quickMessage ||
  !displayArea
) {
  throw new Error("Missing DOM elements on home page");
}

//UTILS
const cleanInput = (input: string): string => {
  return input.trim().replace(/\s+/g, " ");
};

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
  const url: URL = new URL("localhost:8080/api/songs");
  url.searchParams.append("search", lyric);
  const response = await fetch(url.toString());
  if (!response.ok)
    throw new Error(
      "Could not find this song, are you sure you typed it correctly? It may also not be on our database"
    );
  const songDetails: SongDetails = await response.json();
  return songDetails;
};
searchLyricSubmit.addEventListener("click", async (e) => {
  e.preventDefault();

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

//GET
//api/songs/search/lyrics?words=life

//POST
//api/songs/submit

/**SUBMIT
 * 
{
  "name": "Bohemian Rhapsody",
  "lyrics": "Is this the real life? Is this just fantasy?...",
  "releaseYear": 1975,
  "authorNames": ["Freddie Mercury", "Queen"],
  "genreNames": ["Rock", "Progressive Rock"]
}
 */

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

/**
 * [{"id":2,"name":"Yellow Submarine","lyrics":"In the town where I was born Lived a man who sailed to sea And he told us of his life","releaseYear":1980,"authors":[{"id":3,"name":"Beetles"}],"genres":[{"id":3,"name":"Pop"},{"id":4,"name":"Brit Pop"}]}]
 */
