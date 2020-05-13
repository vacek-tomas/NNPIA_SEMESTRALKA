import axios from 'axios';
import AuthService from './AuthService';
import config from '../infrastucture/config';
import {FakturaController_delete, FakturaController_getById, FakturaController_listFaktura, FakturaController_save, FakturaController_update} from '../infrastucture/ApiRoutes';


class FakturaServive {

    fetchFaktury(pageNo = 0, pageSize = 10, sortAsc = true, sortBy ="evidencniCislo") {
        return axios.get(config.API_BASE_URL + FakturaController_listFaktura(pageNo,pageSize, sortAsc, sortBy), AuthService.getAuthHeader());
    }

    fetchFakturaById(id) {
        return axios.get(config.API_BASE_URL + FakturaController_getById(id), AuthService.getAuthHeader());
    }

    deleteFaktura(id) {
        return axios.delete(config.API_BASE_URL + FakturaController_delete(id), AuthService.getAuthHeader());
    }

    addFaktura(faktura) {
        return axios.post(config.API_BASE_URL + FakturaController_save, faktura, AuthService.getAuthHeader());
    }

    editFaktura(id, faktura) {
        return axios.put(config.API_BASE_URL + FakturaController_update(id), faktura, AuthService.getAuthHeader());
    }

}

export default new FakturaServive();
