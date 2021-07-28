function getRoles(u){
    roles=""
    u.roles.forEach(r => {
        roles+=r.role + " ";
    })
    return roles;
}
function doTable(data) {
    //document.getElementById("showUsers").remove();
    let table = $('#showUsers');
    table.empty();
    temp = "";
    data.forEach((u) => {

        temp += "<tr>";
        temp +="<td>"+u.id+"</td>";
        temp +="<td>"+u.name+"</td>";
        temp +="<td>"+u.lastName+"</td>";
        temp +="<td>"+u.email+"</td>";
        temp +="<td>"+getRoles(u)+"</td>";
        temp +="<td><a href='rest/"+u.id+"' type='button' class='btn btn-primary eBtn' >Edit</a></td>";
        temp +="<td><a type='button' href='rest/"+u.id+"' class='btn btn-danger delBtn' >Delete</a></td></tr>";
    })
    document.getElementById("showUsers").innerHTML = temp;

}
function getUsers(){
    fetch('rest/allUsers')
        .then(response => {response.json().then(
            data => {
                console.log(data);
                if (data.length >0) {
                    doTable(data);
                }
            }
        )}).catch(error => {
        console.log(error);
    });
}


$(document).ready(function () {
    getUsers();
    /*$("#showUsers").find('#edit').on('click', (event) => {
        let defaultModal = $('#edit');

        let targetButton = $(event.target);
        let id = targetButton.attr('data-userid');
        let buttonAction = targetButton.attr('data-action');
        fetch(`rest/${id}`).then(response => {
            response.json().then(
                data => {
                    console.log(data);
                    $('#id').val(data.id);
                }
            )
        }).catch(error => {
            console.log(error);
        });
    });*/
})

document.addEventListener('click',function (event){
    event.preventDefault();

    //Delete Modal

    if ($(event.target).hasClass("delBtn")){
        let href = $(event.target).attr("href");
        $("#delete ").modal();
        fetch(href)
            .then(response => {response.json().then(
                u => {
                    console.log(u);
                    $("#delete #idDel").val(u.id);
                    $("#delete #nameDel").val(u.name);
                    $("#delete #lastNameDel").val(u.lastName);
                    $("#delete #emailDel").val(u.email);
                    $("#delete #passwordDel").val(u.password);
                }
            )}).catch(error => {
            console.log(error);
        });

    }
    if ($(event.target).hasClass("submitDel")){
        fetch('rest/'+$("#delete #idDel").val(),{
            method: 'DELETE'
        }).then(function (){
            $("#delete").modal('hide');
            getUsers();
        });

    }

    //Edit Modal

    if ($(event.target).hasClass("eBtn")){
        let href = $(event.target).attr("href");
        $("#edit ").modal();
        fetch(href)
            .then(response => {response.json().then(
                u => {
                    console.log(u);
                    $("#edit #id").val(u.id);
                    $("#edit #name").val(u.name);
                    $("#edit #lastName").val(u.lastName);
                    $("#edit #email").val(u.email);
                    $("#edit #password").val(u.password);
                }
            )}).catch(error => {
            console.log(error);
        });
    }

    if ($(event.target).hasClass("submitE")){
        let data = [];
        let value = $("#edit #selectedRoles  option:selected");
        data.push({id: value.attr("value"),
            role: value.attr("name"),
            authority: value.attr("name")});
        let user = {
            id: $("#edit #id").val(),
            name:$("#edit #name").val(),
            lastName:$("#edit #lastName").val(),
            email:$("#edit #email").val(),
            password:$("#edit #password").val(),
            roles: data
        };
        fetch('rest/edit',{
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(user)
        }).then(function (){
            $("#edit").modal('hide');
            getUsers();
        });

    }

    //Add User


    if ($(event.target).hasClass("submitAdd")){
        let data = [];
        let value = $("#profile #selectedRole  option:selected");
        data.push({id: value.attr("value"),
            role: value.attr("name"),
            authority: value.attr("name")});
        let user = {
            name:$("#profile #nameAdd").val(),
            lastName:$("#profile #lastNameAdd").val(),
            email:$("#profile #emailAdd").val(),
            password:$("#profile #passwordAdd").val(),
            roles: data
        };
        fetch('rest/add',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(user)
        }).then(function (){
            $("#nav-home-tab").click();
            getUsers();
        });

    }




})


