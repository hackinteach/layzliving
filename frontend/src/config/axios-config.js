import Axios from 'axios'
import QS from 'qs'

const URL = process.env.NODE_ENV === 'production' ? 'https://api.layzliving.nuttapat.me/'  : 'http://localhost:8080';
// const URL = 'http://localhost:8080/api';
// const URL = 'http://api.layzliving.nuttapat.me/';
const axios = new Axios.create({
    baseURL: URL,
    withCredentials: true,
    headers: {
        common: {
            'Content-Type' : 'application/x-www-form-urlencoded',
        },
    },

    transformRequest: (data) => QS.stringify(data)
});

export default axios
