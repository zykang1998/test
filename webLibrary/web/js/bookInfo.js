
function openAdd() {
    document.getElementById('addBook').style.display = "block";
}
function closeAdd() {
    document.getElementById('addBook').style.display = "none";
}
function deleteBook(bookId,bookName) {
    if (!confirm("你确定要删除 " + bookId + " 号书籍 "+ bookName +" 吗？"))
    {return;}
    window.location.href = ("../delete?bookId=" + bookId);
}
function updateBook(id,name,price,author,publish) {
    document.getElementById('updateBook').style.display = "block";
    document.getElementById('bookid').value = id;
    document.getElementById('bookname').value = name;
    document.getElementById('bookprice').value = price;
    document.getElementById('bookauthor').value = author;
    document.getElementById('publishhouse').value = publish;
}
function closeUp() {
    document.getElementById('updateBook').style.display = "none";
}

