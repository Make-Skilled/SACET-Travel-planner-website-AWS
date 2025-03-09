function deleteResource(resourceType, id) {
    if (confirm('Are you sure you want to delete this ' + resourceType + '?')) {
        fetch('/' + resourceType + '/' + id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (response.ok) {
                window.location.href = '/' + resourceType;
            } else {
                alert('Failed to delete ' + resourceType + '. Please try again.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('An error occurred while deleting the ' + resourceType + '.');
        });
    }
}
