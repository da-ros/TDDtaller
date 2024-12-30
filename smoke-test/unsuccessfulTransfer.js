import http from 'k6/http';
import { check, sleep } from 'k6';
import { randomIntBetween } from
'https://jslib.k6.io/k6-utils/1.2.0/index.js';

export const options = {
vus: 3, // Key for Smoke test. Keep it at 2, 3, max 5 VUs
duration: '1m', // This can be shorter or just a few iterations
thresholds:{
    http_req_duration: ['p(90)<5000']
}
};

const payload = JSON.stringify({
    fromAccount: "123-456",
    toAccount: "789-101",
    amount: 600.0,
    description: "Payment for services"
  });

const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

export default () => {
    const urlRes = http.post('http://acme-bank-service:8080/bank/transfer', payload, params);
    
    check(urlRes, {
      'status is 400': (r) => r.status <= 400,
      'error message contains "greather than available"': (r) => r.body.includes('greather than available')
    });

    sleep(1);
};