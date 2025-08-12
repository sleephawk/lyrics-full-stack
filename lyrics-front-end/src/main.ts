import "./style.scss";

const cleanInput = (input: string): string => {
  return input.trim().replace(/\s+/g, " ");
};

// so needs to look through the database to find a song by lyric.
//it only has the song so it needs to search the field
const searchSong = async (input: string) => {
  const lyric = cleanInput(input);
  const url = new URL("http://localhost:8080/api/songs");
  url.searchParams.append("search", lyric);
  const response = await fetch(url.toString());
  if (!response.ok)
    throw new Error(
      "Could not find this song, are you sure you typed it correctly?"
    );
  const songDetails = response.json();
  return songDetails;
};
