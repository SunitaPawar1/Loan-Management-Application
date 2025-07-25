/**
 * 
 */
 
async function fetchUserProfile() {
    try {
        const response = await fetch('/profile', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const data = await response.json();

        document.getElementById('fullName').textContent += data.fullName;
        document.getElementById('email').textContent += data.email;
        document.getElementById('phone').textContent += data.phone;
        document.getElementById('status').textContent += data.status;

    } catch (error) {
        console.error('Error fetching user profile:', error);
    }
}

// Call the function to fetch and display the user profile
fetchUserProfile();