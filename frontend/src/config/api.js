import Axios from '../config/axios-config';

/* Credential Stuffs */
export function register(username, password, repeatPassword, email, firstname, lastname) {
    return Axios.post('/register', {
        username: username,
        password: password,
        email: email,
        firstName: firstname,
        lastName: lastname,
        repeatPassword: repeatPassword,
    })
        .then(function (response) {
            console.log(response);
            return response;
        })
        .catch(function (error) {
            console.log(error);
        });
}

export function login(username, password) {
    return Axios.post('/login', {
        username: username,
        password: password,
    })
        .then(response => response)
        // .catch(({response: {data, status, message}}) => {
        //     console.log("err", data, status, message);
        //     // if(error.response.status === 401){
        //     //     console.log("Login failed");
        //     // }
        // });
}

export function whoami() {
    return Axios.get('/whoami')
}

export function logout() {
    return Axios.get('/logout')
}

/* User's Stuffs */
export function addHouse(houseName, streetAddress, city, country, zipCode) {
    return Axios.post('/add_house', {
        houseName, streetAddress, city, country, zipCode
    })
}

/* Houses Stuff */

export function getHouses() {
    return Axios.get('/user/houses')
        .then(response => {
            return response;
        })
}

export function getHouseEnergyByDate(houseId, dd, mm, yyyy) {
    return Axios.get('/house/energy', {
      params: {
        houseId,
        dd,
        mm,
        yyyy
      }
    }).then(response => response);
}
export function removeHouse(houseId){
    return Axios.post('/house/remove',{
        houseId: houseId
    }).then(response => {
        return response;
    });
}

export function getAllDevicesInHouse(houseId) {
    return Axios.get('/house/all_devices', {
      params: {
        houseId
      }
    }).then(response => response);
}

export function addRoom(roomName, houseId) {
    return Axios.post('/add_room', {
        roomName, houseId
    }).then(response => response);
}
export function removeRoom(roomId){
    return Axios.post('/room/remove',{
        roomId: roomId
    }).then(response => {
        return response;
    });
}


/* Room's Stuffs  */
export function getDevicesInRoom(roomId) {
    return Axios.get('/room/devices', {
      params: {
        roomId
      }
    }).then(response => response);
}

export function getAllDevices() {
    return Axios.get('/all_devices_types').then(response => response);
}

export function getRooms(houseId) {
    return Axios.get('/all_rooms', {
        params: {
          houseId
        }
    }).then(response => response)
}

export function getRoomList(houseId){
  return Axios.get('/room/list', {
    params: {
      houseId,
    }
  }).then(response => {
    return response;
  });
}

export function getHouseList(){
  return Axios.get('/user/housesList')
    .then(response => response);
}

export function checkUsernameIsOk() {
    return Axios.post('/checkUsernameIsOk')
        .then(response => response)
}

export function checkEmailIsOk(email) {
    return Axios.post('/checkEmailIsOk', {
        email
    }).then(response => {
        return response.data;
    });

}


export function addDevice(deviceName, deviceType,roomId){
    return Axios.post('/device/add_device',{
        deviceName: deviceName,
        deviceType: deviceType,
        roomId: roomId
    }).then(response => {
        return response;
    });
}

export function removeDevice(deviceId){
    return Axios.post('/device/remove',{
        deviceId: deviceId
    }).then(response => {
        return response;
    });
}

export function toggleDevice(deviceId){
    return Axios.post('/device/on_off',{
        deviceId: deviceId
    }).then(response => {
        return response;
    });
}
///device/energy_consumed

export function deviceEnergyConsumed(deviceId,dd,mm,yyyy){
    return Axios.post('/device/energy_consumed',{
        deviceId: deviceId,
        dd: dd,
        mm:mm,
        yyyy: yyyy
    }).then(response => {
        return response;
    });
}

///all_devices_types

export function allDeviceTypes(){
    return Axios.post('/all_devices_types').then(response => {
        return response;
    });
}