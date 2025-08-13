export const sanitiseInput = (input: string): string => {
  return input
    .trim()
    .replace(/\s+/g, " ")
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;")
    .replace(/'/g, "&#39;");
};

export const sleep = (ms: number) =>
  new Promise((resolve) => setTimeout(resolve, ms));
