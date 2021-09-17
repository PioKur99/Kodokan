import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FamilyMember } from '../data/family-member';

@Injectable({
  providedIn: 'root'
})
export class FamilyService {

  constructor(private http: HttpClient) { }
  baseUrl: String = "http://172.18.0.3:8081/"

  getFamilyMembers(id: Number):Observable<FamilyMember[]> {
    let url = this.baseUrl + `customer/${id}/familyWithNames`
    return this.http.get<FamilyMember[]>(url)
  }

  deleteFamilyMember(id: String): Observable<any> {
    let url = this.baseUrl + `family/delete-customer-from-family/${id}`;
    return this.http.delete<any>(url)
  }
}
