export function signIn(data) {
  return fetch("/user/login", {
    body: `name=${data.name}&password=${data.password}`,
    method: "POST",
    headers: {
      'content-type': 'application/x-www-form-urlencoded'
    },
  })
    .then((res) => {
      return res.json();
    })
    .catch((err) => {
      console.error(err) 
    });
}


export function signUp(data){
  return fetch("/user/register",{
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

