export function reqAllEssay() {
  return fetch("/essay/all",{
    method:'POST'
  })
    .then((res) => {
      return res.json();
    })
    .catch((err) => {
      console.error(err);
    });
}
