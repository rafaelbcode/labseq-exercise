import {Component, inject, Injectable} from '@angular/core';
import {FormControl, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-labseq',
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

    const baseUrl = "http://localhost:8080";

    this.http.get(baseUrl+`/labseq/${queryIndex}`)
      .subscribe({
        next: (data) => {
          this.calculationResult = data;
          this.isLoading = false;
        },
        error: (err) => {
          this.calculationResult = "An error has ocurred, negative values aren't allowed."
          this.isLoading = false;
        }
      });
  }
}

/**
 * next: (data) => {
 *           this.calculationResult = data;
 *           this.isLoading = false;
 *         },
 *         error: () => {
 *           this.calculationResult = 'Error occurred';
 *           this.isLoading = false;
 *         }
 */
