import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { APPComponent } from './app/app';

bootstrapApplication(APPComponent, appConfig)
  .catch((err) => console.error(err));
