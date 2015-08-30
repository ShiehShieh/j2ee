/**
 * Created by JoshOY on 2015/4/26.
 */

var uploadItemInfo = function() {
    var mid_id = $('#input-item-id').val();
    var type_id = $('#input-item-type').val();
    var detail = $('#input-item-detail').val();
    var name = $('#input-item-name').val();
    var num = ($('#input-item-quality').val());

    if (mid_id === '') {
        alert('Please insert item ID!');
        return;
    }

    $.post('/Admin/Goods.json',
        {
            'input-item-id': parseInt(mid_id),
            'input-item-type': type_id,
            'input-item-detail': detail,
            'input-item-name': name,
            'input-item-quality': num
        },
        function(data,status) {
            alert('RESPONSE:\ndata: ' + data + '\nstatus: ' + status);
        }
    );

    var file = ($(':file').val());

    $.post('/upload',
        {
            'input-item-id': parseInt(mid_id),
            'file': file
        },
        function(data,status) {
            alert('RESPONSE:\ndata: ' + data + '\nstatus: ' + status);
        }
    );
};


