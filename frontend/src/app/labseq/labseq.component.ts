import {Component, inject, Injectable} from '@angular/core';
import {FormControl, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {NgIf} from '@angular/common';
import {environment} from '../../environments/environment';

interface LabseqResponse {
  result: string;
}

@Component({
  selector: ' app-labseq',
  imports: [ReactiveFormsModule, NgIf, FormsModule],
  templateUrl: './labseq.component.html',
  styleUrl: './labseq.component.css'
})
@Injectable({providedIn: 'root'})
export class LabseqComponent {
  labseqIndex = new FormControl('');
  private http = inject(HttpClient);
  isLoading = false;
  calculationResult: any;

  onCalculate() {
    const queryIndex = this.labseqIndex.value;
    if (!queryIndex) return;

    this.isLoading = true;

    console.log('API URL:', environment.apiUrl);

    this.http.get<LabseqResponse>(
      `${environment.apiUrl}/labseq/${queryIndex}`
    )
      .subscribe({
        next: (data) => {
          this.calculationResult = data.result;
          this.isLoading = false;
        },
        error: (err) => {
          this.calculationResult = "An error has occurred. Only non-negative integer numbers are allowed."
          this.isLoading = false;
        }
      });
  }
}

