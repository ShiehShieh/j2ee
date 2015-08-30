/**
 * Created by JoshOY on 2015/4/26.
 */

var confirmBook = function(){
    var id = $('#input-confirm-id').val();
    if (id === '') {
        alert('Please insert book ID.');
    }

    $.ajax({
        url: '/Admin/Book.json',
        type: 'PUT',
        data: {
            "book_id": parseInt(id),
            "type": 2
        },
        success: function(result){
            alert('POST SUCCESS!');
            console.log(result);
        }
    });
};

var confirmDeal = function() {
    var id = $('#input-confirm-id').val();
    if (id === '') {
        alert('Please insert book ID.');
    }

    $.ajax({
        url: '/index.php/Admin/Book.json',
        type: 'PUT',
        data: {
            "book_id": parseInt(id),
            "type": 1
        },
        success: function(result){
            alert('POST SUCCESS!');
            console.log(result);
        }
    });
};


var confirmUserCancel = function() {
    var id = $('#input-confirm-id').val();
    if (id === '') {
        alert('Please insert book ID.');
    }

    $.ajax({
        url: '/index.php/Admin/Book.json',
        type: 'PUT',
        data: {
            "book_id": parseInt(id),
            "type": 3
        },
        success: function(result){
            alert('POST SUCCESS!');
            console.log(result);
        }
    });
};