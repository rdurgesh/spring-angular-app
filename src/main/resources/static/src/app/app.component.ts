import { Component, OnInit } from '@angular/core';
import { FileService } from './file.service';
import { saveAs } from 'file-saver/FileSaver';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'My File Store App';
  files = [];
  constructor(private fileService: FileService) {
  }

  getAllFiles(): void {
    this.fileService.getAllFiles().subscribe(files => {
      this.files = files;
    });
  }

  ngOnInit(): void {
    this.getAllFiles();
  }

  downloadFile(file): void {
    this.fileService.downloadFile(file).subscribe(response => {
      console.log(response);
      const contentDispositionHeader: string = response.headers.get('Content-Disposition');
      const parts: string[] = contentDispositionHeader.split(';');
      const filename = parts[1].split('=')[1];
      const blob = new Blob([response._body], { type: 'application/octet-stream' });
      saveAs(blob, filename);
    });
  }
}
