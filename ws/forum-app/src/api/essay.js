export function reqAllEssay() {
  return fetch("/essay/all", {
    method: "POST",
  })
    .then((res) => {
      return res.json();
    })
    .catch((err) => {
      console.error(err);
    });
}

export function reqPublishEssay(data) {
  return fetch("/essay/writePost", {
    body: JSON.stringify(data),
    method: "POST",
    headers: {
      "content-type": "application/json",
    },
  })
    .then((res) => {
      return res.json();
    })
    .catch(() => {});
}

export function reqMyEssay(data) {
  return fetch("/essay/byId", {
    body: JSON.stringify(data),
    method: "POST",
    headers: {
      "content-type": "application/json",
    },
  })
    .then((res) => res.json())
    .catch(() => {});
}

export function reqEssayDetail(data) {
  return fetch("/essay/find", {
    body: JSON.stringify(data),
    method: "POST",
    headers: {
      "content-type": "application/json",
    },
  })
    .then((res) => res.json())
    .catch(() => {});
}

export function reqPublishComment(data) {
  return fetch("/floor/write", {
    body: JSON.stringify(data),
    method: "POST",
    headers: {
      "content-type": "application/json",
    },
  })
    .then((res) => res.json())
    .catch(() => {});
}

export function reqReplyToComment(data) {
  return fetch("/layer/write", {
    body: JSON.stringify(data),
    method: "POST",
    headers: {
      "content-type": "application/json",
    },
  })
    .then((res) => res.json())
    .catch(() => {});
}

export function reqDelFloor(data) {
  return fetch("/floor/delete", {
    body: JSON.stringify(data),
    method: "POST",
    headers: {
      "content-type": "application/json",
    },
  })
    .then((res) => res.json())
    .catch(() => {});
}


export function reqDelLayer(data) {
  return fetch("/layer/delete", {
    body: JSON.stringify(data),
    method: "POST",
    headers: {
      "content-type": "application/json",
    },
  })
    .then((res) => res.json())
    .catch(() => {});
}