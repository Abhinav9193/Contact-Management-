const baseUrl = "http://localhost:9090/api/contact";

// Add Contact
function addContact() {
    const username = document.getElementById("username").value;
    const email = document.getElementById("email").value;
    const contactNo = document.getElementById("contactNo").value;

    if (!username || !email || !contactNo) {
        alert("Please fill all fields!");
        return;
    }

    const contact = { username, email, contactNo };

    fetch(`${baseUrl}/save`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(contact)
    })
    .then(response => response.json())
    .then(data => {
        alert("Contact added successfully!");
        fetchContacts();
    })
    .catch(error => alert("Error adding contact!"));
}

// Fetch & Display Contacts
function fetchContacts() {
    fetch(baseUrl)
    .then(response => response.json())
    .then(contacts => {
        const contactsList = document.getElementById("contactsList");
        contactsList.innerHTML = "";
        contacts.forEach(contact => {
            const li = document.createElement("li");
            li.innerHTML = `${contact.id} - ${contact.username} (${contact.email}, ${contact.contactNo})
            <button class="delete-btn" onclick="deleteContact(${contact.id})">Delete</button>`;
            contactsList.appendChild(li);
        });
    })
    .catch(error => alert("Error fetching contacts!"));
}

// Delete Contact by ID (From UI)
function deleteById() {
    const id = document.getElementById("deleteId").value;
    if (!id) {
        alert("Enter an ID!");
        return;
    }
    deleteContact(id);
}

// Delete Contact Function
function deleteContact(id) {
    if (!confirm("Are you sure?")) return;

    fetch(`${baseUrl}/${id}`, { method: "DELETE" })
    .then(response => response.text())
    .then(message => {
        alert(message);
        fetchContacts();
    })
    .catch(error => alert("Error deleting contact!"));
}

// Search Contact by Username
function searchContact() {
    const username = document.getElementById("searchUsername").value;
    if (!username) {
        alert("Enter a username!");
        return;
    }

    fetch(`${baseUrl}/username/${username}`)
    .then(response => response.json())
    .then(contact => {
        if (contact.username) {
            document.getElementById("searchResult").innerText = 
                `ID: ${contact.id}, Email: ${contact.email}, Contact No: ${contact.contactNo}`;
        } else {
            document.getElementById("searchResult").innerText = "Contact not found!";
        }
    })
    .catch(error => alert("Error searching contact!"));
}

// Toggle Contact List
function toggleContacts() {
    const contactsContainer = document.getElementById("contactsContainer");
    if (contactsContainer.classList.contains("hidden")) {
        contactsContainer.classList.remove("hidden");
        fetchContacts();
    } else {
        contactsContainer.classList.add("hidden");
    }
}
