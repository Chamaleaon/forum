export function signIn(data) {
  fetch("/user/login", {
    body: JSON.stringify(data),
    method: "POST",
    headers: {
      'content-type': 'application/x-www-form-urlencoded'
    },
  })
    .then((res) => {
      console.log(res)
      return res.json();
    })
    .catch((err) => {
      console.error(err)
    });
}


export function signUp(data){
  fetch("/user/register",{
    body:JSON.stringify(data),
    method:"POST",
    headers: {
      'content-type': 'application/json'
    },
  }).then((res)=>{
    return res.json()
  }).catch(err=>{
    console.error(err)
  })
}
