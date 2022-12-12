app.controller("product-ctrl", function ($scope, $http) {
    $scope.items = [];
    $scope.form = {};
    $scope.cates = [];

    $scope.initialize = function () {
        //load toàn bộ ttin product
        $http.get("/rest/products").then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.createDate = new Date(item.createDate);
            })
        })
        //load category
        $http.get("/rest/categories").then(resp => {
            $scope.cates = resp.data;
        })
    }
    //Khởi đầu
    $scope.initialize();
    //Xóa form
    $scope.reset = function () {
        $scope.form = {
            createDate: new Date(),
            image: 'cloud-upload.jpg',
            available: true,
        };
    }
    //Hiển thị lên
    $scope.edit = function (item) {
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(0)").tab(`show`)
    }

    //Create
    $scope.create = function () {
        var item = angular.copy($scope.form);
        $http.post("/rest/products", item).then(resp => {
            resp.data.createDate = new Date(resp.data.createDate)
            $scope.items.push(resp.data);
            $scope.reset();
            alert("Thêm mới hoàn tất");
            $scope.initialize();
        }).catch(error => {
            alert("Lỗi khi thêm mới");
            console.log(error);
        })
    }

    //Update
    $scope.update = function () {
        var item = angular.copy($scope.form);
        $http.put('/rest/products/${item.id}', item).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id)
            $scope.items[index] = item;
            alert("Cập nhật hoàn tất");
            console.log(resp.data)
        }).catch(error => {
            alert("Lỗi khi cập nhật");
            console.log(error);
        })
    }
    //Delete
    $scope.delete = function (item) {
        item.available = item.available == true ? false : true
        $http.put('/rest/products/${item.id}', item).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id)
            $scope.items[index] = item;
            alert("Thay đổi trạng thái hoàn tất");
            console.log(resp.data)
        }).catch(error => {
            alert("Lỗi khi cập nhật");
            console.log(error);
        })
        // $http.delete(`/rest/products/${item.id}`, item).then(resp => {
        //     var index = $scope.items.findIndex(p => p.id == item.id)
        //     $scope.items.splice(index, 1);
        //     $scope.reset();
        //     alert("Xóa hoàn tất");
        // }).catch(error => {
        //     alert("Lỗi khi xóa");
        //     console.log(error);
        // })
    }

    //Upload ảnh

    $scope.imageChanged = function (files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            $scope.form.image = resp.data.name;
        }).catch(error => {
            alert("Lỗi upload")
            console.log("Error", error);
        })

    }

    $scope.pager = {
        page: 0,
        size: 10,
        get item() {
            var start = this.page * this.size;
            return  $scope.items.slice(start, start + this.size);
        },
        get count(){
            return Math.ceil(1.0*$scope.items.length/ this.size)
        },
        first(){
            this.page=0;
        },
        prev(){
            this.page--;
            if (this.page<0){
                this.last();
            }
        },
        next(){
            this.page++;
            if (this.page >= this.count){
                this.first();
            }
        },
        last(){
            this.page = this.count-1;
        }

    }
});