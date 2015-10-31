function ChangeDiv(divDesc) {
    document.getElementById('tab_gdlg').style.display = "none";
    document.getElementById('tab_gdwk').style.display = "none";
    document.getElementById('tab_swny').style.display = "none";
    document.getElementById('tab_gzjy').style.display = "none";
    document.getElementById('tab_zzjy').style.display = "none";
    document.getElementById('tab_jsjy').style.display = "none";
    document.getElementById('tab_' + divDesc).style.display = "block";
    $("#gdlg").removeClass("select");
    $("#gdwk").removeClass("select");
    $("#gzjy").removeClass("select");
    $("#zzjy").removeClass("select");
    $("#swny").removeClass("select");
    $("#jsjy").removeClass("select");
    $("#"+divDesc).addClass("select");
}

function showMa(type){
    $("#wbId").hover(function(){
        $("#wbcode").css("display","block");
    },function(){
        $("#wbcode").css("display","none");
    });
    $("#wxId").hover(function(){
        $("#wxcode").css("display","block");
    },function(){
        $("#wxcode").css("display","none");
    });
}