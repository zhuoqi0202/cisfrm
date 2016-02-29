/**
 * Created by wangchg on 16-2-12.
 */
fis.set('project.ignore', [
    '*.iml',
    'bower.json',
    'package.json',
    'fis-conf.js',
    'node_modules/**',
    'bower_components/**',
    '.git/**',
    '.svn/**',
    'run.bat',
    'run.sh'
]);
fis.match('{bower_components/**,images/**,scripts/**,styles/**}', {
    release: '/static/$0'
});
//fis.match('scripts/controllers/**', {
//    packTo: '/scripts/controllers.js'
//});
fis.config.set('settings.optimizer.uglify-js', {
    mangle: false,
    preserveComments: false
});

fis.match('styles/*.less', {
    parser: fis.plugin('less'),
    rExt: '.css'
});
fis.match('::package', {
    spriter: fis.plugin('csssprites')
});
fis.match('styles/**', {
    useSprite: true
});
fis.match('styles/*.css', {
    optimizer: fis.plugin('clean-css')
});
fis.match('scripts/**', {
    useHash: true,
    optimizer: fis.plugin('uglify-js')
});
fis.match('::package', {
    postpackager: fis.plugin('loader', {
        allInOne: {
            js: 'static/scripts/${filepath}.js',
            css:'styles/${filepath}.css',
            ignore: 'node_modules/**'
        }
    })
});
fis.match('*', {
    deploy: fis.plugin('local-deliver', {
        to: '../target'
    })
});
