//Khai báo module ứng dụng nằm ở đâu
const app = angular.module("shopping-cart-app", []);
//Định nghĩa 1 cntroller tương ứng với giao diện
app.controller("shopping-cart-ctrl", function ($scope, $http) {
    // alert("Thông báo ok");

// QUẢN LÝ GIỎ HÀNG

    $scope.cart = {
        items: [],
        //Thêm sản phẩm
        add(id) {
            // tìm xem trong items có item đã click vào không
            var item = this.items.find(item => item.id == id);
            // tải 1 sp trên máy chủ (server) về thông qua 1 api
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`).then(resp => {
                    resp.data.qty = 1;
                    //thêm lại vào dsach
                    this.items.push(resp.data)
                    this.saveToLocalStorage();
                })
            }
        },
        //Tính tổng số lượng mặt hàng trong giỏ
        get count() {
            return this.items
                .map(item => item.qty)
                // tổng của tất cả các phần tử trong một mảng
                .reduce((total, qty) => total = total + qty, 0)

        },
        //Tính tổng tiền
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                // tổng toàn bộ  tất cả các phần tử trong một mảng
                .reduce((total, qty) => total = total + qty, 0)
        },
        //Lưu giỏ hàng vào localstorage
        saveToLocalStorage() {
            //Chuyển đổi mặt hàng sang file json
            var json = JSON.stringify(angular.copy(this.items))
            //lưu vào localstorage với tên là ...
            localStorage.setItem("cart", json);
        },
        //lấy và đọc giỏ hàng từ localstorage
        loadLocalStorage() {
            //lấy item và đọc lại từ localstorage
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        },

        remove(id) {
            var index = this.items.findIndex(item => item.id == id);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },
        clear() {
            this.items = [];
            this.saveToLocalStorage();
        }


    }

    $scope.cart.loadLocalStorage();
    // $scope.order={
    //     createDate : new Date(),
    //     address: "",
    //     account:{
    //         username:${"#username".text()},
    //         get orderDetails(){
    //             return $scope.cart.items.map(item =>{
    //                 return{
    //                     product: {id: item.id},
    //                     price: item.price,
    //                     quantity: item.qty
    //                 }
    //             })
    //         },
    //         purchase() {
    //             var order = angular.copy(this);
    //             //Đặt hàng
    //             $http.post("/rest/orders", order).then(resp => {
    //                 alert("Đặt hàng thành công");
    //                 $scope.cart.clear();
    //                 location.href = "/order/detail/" + resp.data.id;
    //             }).catch(error => {
    //                 alert("Đặt hàng lỗi")
    //                 console.log(error)
    //             })
    //         }
    //     }
})