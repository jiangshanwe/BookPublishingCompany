function $$(id) {
    return typeof id == 'string' ? document.getElementById(id) : id;
}
window.onload = function () {
    //获取鼠标滑过或点击的标签和要切换内容的元素
    var titles = document.getElementById('product_tit').getElementsByTagName('li');
    divs = $$('product-con').getElementsByTagName('div');
    if (titles.length != divs.length)
        return;
    //遍历titles下所有的li
    for (var i = 0; i < titles.length; i++) {
        titles[i].id = i;
        titles[i].onmouseover = function () {
            for (var j = 0; j < titles.length; j++) {
                titles[j].className = '';
                divs[j].style.display = 'none';
            }
            this.className = 'select';
            divs[this.id].style.display = 'block';

        }
    }
}

function $$(id) {
    return typeof id == 'string' ? document.getElementById(id) : id;
}
window.onload = tab;
function tab() {
    //当前高亮显示内容的索引
    var index = 0;
    var timer = null;

    //获取所有页签
    var list = document.getElementById('central-tit').getElementsByTagName('li');
    var divs = $$('central-con').getElementsByTagName('div');
    // 遍历所有页签且给他们绑定事件
    for (var i = 0; i < list.length; i++) {
        list[i].id = i;
        list[i].onmouseover = function () {
            clearInterval(timer);
            changeOption(this.id);

        }
        list[i].onmouseout = function () {
            timer = setInterval(autoplay, 4000);

        }
    }
    if (timer) {
        clearInterval(timer);
        timer = null;
    }
// 添加定时器，改变高亮的索引
    timer = setInterval(autoplay, 4000);

    function autoplay() {
        index++;
        if (index >= list.length) {
            index = 0;
        }
        changeOption(index);
    }

    function changeOption(curIndex) {

        for (var j = 0; j < list.length; j++) {
            list[j].className = '';
            divs[j].style.display = 'none';
        }
        list[curIndex].className = 'select';
        divs[curIndex].style.display = 'block';
        index = curIndex;
    }
}

// 产品展示JS





