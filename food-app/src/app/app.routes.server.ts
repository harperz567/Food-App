import { RenderMode, ServerRoute } from '@angular/ssr';

export const serverRoutes: ServerRoute[] = [
  {
    // Server side 
    path: 'food-catalog/:id',
    renderMode: RenderMode.Client  // Or RenderMode.Server
  },
  {
    // All others
    path: '**',
    renderMode: RenderMode.Prerender
  }
];