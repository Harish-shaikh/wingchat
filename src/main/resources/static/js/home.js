var stompClient = null;
var username = null;
var recipient = null;

function connect() {
  let socket = new SockJS("/serverConnection");
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    console.log("Connected: " + frame);
    stompClient.subscribe(
      `/user/${username}/queue/messages`,
      function (response) {
        const message = JSON.parse(response.body);
        showMessage(message.sender, message.content, false); // Display received message
      }
    );
  });
}

function sendMessage(message) {
  if (message !== "") {
    let jsonOb = {
      sender: username,
      receiver: recipient,
      content: message,
    };
    stompClient.send(`/app/chat/${recipient}`, {}, JSON.stringify(jsonOb));
    displayMessage("You", message, true);
  }
}

function displayMessage(sender, content, isSent) {
  const messageDiv = document.createElement("div");
  messageDiv.className = isSent ? "sent-message" : "received-message";
  messageDiv.textContent = `${sender}: ${content}`;
  document.getElementById("messages").appendChild(messageDiv);
}

function showMessage(sender, content, isSent) {
  displayMessage(sender, content, isSent);
}

$(document).ready(function () {
  username = document.getElementById("usernames").getAttribute("data-username");

  $(".connect-button").click(function () {
    recipient = $(this).data("recipient");
    document.getElementById("recipientUsername").textContent = recipient;
    connect();
  });

  $("#send").click(function () {
    var messageInput = document.getElementById("messageInput");
    var message = messageInput.value.trim();
    sendMessage(message);
    messageInput.value = "";
  });
});
///End one to one chat application java scirpt part

// worls chat java script part

// this part onl for toggle

$(document).ready(function () {
  $("#show-registration-link").click(function () {
    toggleRegistrationForm();
    closeLoginForm();
  });

  $("#show-Login-link").click(function () {
    toggleLoginForm();
    closeRegistrationForm();
  });

  function toggleLoginForm() {
    var loginForm = $("#login-Form");

    if (loginForm.css("display") === "none") {
      loginForm.css("display", "block");
    } else {
      loginForm.css("display", "none");
    }
  }

  function toggleRegistrationForm() {
    var registrationForm = $("#newRegistrationForm");

    if (registrationForm.css("display") === "none") {
      registrationForm.css("display", "block");
    } else {
      registrationForm.css("display", "none");
    }
  }

  function closeLoginForm() {
    $("#login-Form").css("display", "none");
  }

  function closeRegistrationForm() {
    $("#newRegistrationForm").css("display", "none");
  }
});
