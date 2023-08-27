var stompClient = null;

function sendMessageWorld() {
  let jsonOb = {
    name: localStorage.getItem("name"),
    content: $("#message-value").val(),
  };

  stompClient.send("/app/sendmessage", {}, JSON.stringify(jsonOb));
}

function connects() {
  let socket = new SockJS("/serverConnection");
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    console.log("Connected : " + frame);

    $("#name-from").addClass("d-none");
    $("#chat-room").removeClass("d-none");

    // Subscribe to the user's personalized chat channel
    stompClient.subscribe("/user/worldjoin", function (response) {
      showMessageWorld(JSON.parse(response.body));
    });
  });
}

function showMessageWorld(message) {
  $("#message-container-table").prepend(
    `<tr><td><b>${message.name} :</b> ${message.content}</td></tr>`
  );
}

$(document).ready(() => {
  $("#connect").click(() => {
    // let name=$("#name-value").val()
    // localStorage.setItem("name",name)
    // $("#name-title").html(`Welcome , <b>${name} </b>`)
    connects();
  });

  $("#worldsend").click(() => {
    var messageInput = document.getElementById("message-value");
    var message = messageInput.value.trim();
    sendMessageWorld(message);
    messageInput.value = "";
    // sendMessageWorld();
  });
});
