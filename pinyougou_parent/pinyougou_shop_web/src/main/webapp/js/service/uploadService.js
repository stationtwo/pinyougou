/**
 * 文件上传 服务层
 */
app.service('uploadService', function ($http) {
    this.uploadFile = function () {
        var formData = new FormData;
        formData.append('file', file.files[0]);
        return $http({
            method: 'POST',
            eventHandlers: {
                progress: function (c) {
                    console.log('Progress -> ' + c);
                    console.log(c);
                }
            },
            uploadEventHandlers: {
                progress: function (e) {
                    console.log('UploadProgress -> ' + e);
                    console.log(e.loaded / e.total * 100);
                }
            },
            url: '../upload.do',
            data: formData,
            headers: {'Content-Type':undefined},
            transformRequest: angular.identity
        })
    };
});