export function reqAllEssay() {
  return fetch("/essay/all",{
    method:'POST',
  })
    .then((res) => {
      return res.json();
    })
    .catch((err) => {
      console.error(err);
    });
}


export function reqPublishEssay(data){
  return fetch("/essay/writePost",{
    body:JSON.stringify(data),
    method:'POST',
    headers: {
      'content-type': 'application/json'
    },
  }).then(res=>{
    return res.json()
  }).catch(()=>{})
}